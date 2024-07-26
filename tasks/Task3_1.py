import re
import numpy as np
import cv2
from PIL import Image

from Basis import Coordinate, draw_rectangle, draw_line, get_text_size, draw_text, crop_and_combine
from bus import draw_bus_Boolean


def connect_points(canvas, text_canvas, points, size, split_function, rectangle_text="", force_Output=""):
    exits = []

    all_x = [el.x for el in points]
    all_y = [el.y for el in points]

    min_point_x, max_point_x = min(all_x), max(all_x)
    min_point_y, max_point_y = min(all_y), max(all_y)

    join_sub_function = "".join(split_function)

    isOnlyLineNeeded = len(join_sub_function) == 2 and "/" in join_sub_function
    if not isOnlyLineNeeded:
        isOnlyLineNeeded = len(join_sub_function) == 1

    if isOnlyLineNeeded:
        line_end = Coordinate(min_point_x, min_point_y)
        line_end = draw_line(canvas, line_end, line_end + Coordinate(size + size, 0),
                             text_canvas=text_canvas)
    else:
        first_point = Coordinate(min_point_x, min_point_y)
        second_point = Coordinate(max_point_x, max_point_y)

        if force_Output != "":
            join_sub_function = force_Output

        rectangle_end = draw_rectangle(canvas, first_point, second_point, size, text_outside=join_sub_function,
                                       text_inside=rectangle_text, text_canvas=text_canvas)

        line_end = Coordinate(size, 0)
        line_end = draw_line(canvas, rectangle_end, rectangle_end + line_end, text_canvas=text_canvas)
    exits.append(line_end)

    return exits


def solve_Task_3_1_scheme(function):
    # Оголошуємо змінні
    last_coord_addition_text = Coordinate(0, 0)
    coordinates = []
    function_without_inv = function.replace("/", "")  # функція без інверторів
    full_function = function.replace(" v ", "")  # функція без елементів АБО
    split_function = function.split(" v ")  # функція розділена на масив по одній матриці елементів І
    starting_coords = Coordinate(400, 100)  # початкові координати шини
    element_size = 100  # розмір всіх елементів також працює як маштаб

    inputs_len = sum([len(matrix) for matrix in function_without_inv.split(" v ")])
    bus_combinations = re.findall(r'/[a-z]|[a-z]', full_function)
    invertors = [element.replace("/", "") for element in bus_combinations]
    invertors_len = len(list(dict.fromkeys(invertors)))

    image_y: int = (invertors_len + inputs_len) * element_size + starting_coords.y + element_size * 2
    image = np.ones((image_y, 1500, 3), dtype=np.uint8) * 255  # зображення в яке збережеться схема
    pil_image = Image.new("RGBA", (image.shape[1], image.shape[0]), (255, 255, 255, 0))

    # малюємо шину
    exit_from_bus = draw_bus_Boolean(image, starting_coords, element_size, full_function, pil_image)

    # обчислюємо кількість матриць
    all_matrix = [len(matrix) for matrix in function_without_inv.split(" v ")]
    all_matrix = list(dict.fromkeys(all_matrix))
    all_matrix = [matrix for matrix in all_matrix if matrix != 1]

    # малюємо матриці
    if len(all_matrix) != 1:
        size = get_text_size("матриця")
        matrix_text = exit_from_bus[-1] + Coordinate(element_size // 2, element_size)
        matrix_text -= Coordinate(size[0] // 2, 0)

        draw_text(pil_image, matrix_text, "матриця")
        matrix_text = matrix_text + Coordinate(element_size // 2, 0)

        for element in all_matrix:
            matrix_text = matrix_text + Coordinate(0, element_size // 2)
            draw_text(pil_image, matrix_text, f"{element}I")
    else:
        size = get_text_size("елемент")
        element_text = exit_from_bus[-1] + Coordinate(element_size // 2, element_size)
        element_text -= Coordinate(size[0] // 2, 0)
        draw_text(pil_image, element_text, "елемент")
        element_text = element_text + Coordinate(element_size // 2, 0)
        element_text = element_text + Coordinate(0, element_size // 2)
        draw_text(pil_image, element_text, f"{all_matrix[0]}I")
        matrix_text = element_text

    # малюємо усі І
    if len(exit_from_bus) > 1:
        points_used = 0

        for element in split_function:
            points_len = len(element.replace("/", ""))
            points = exit_from_bus[points_used:points_len + points_used]
            sub_function = re.findall(r'/[a-z]|[a-z]', element)
            if len(split_function) == 1:
                force_Output = "f0"
            else:
                force_Output = ""
            coordinates_points = connect_points(image, pil_image, points, element_size, sub_function,
                                                rectangle_text="&", force_Output=force_Output)
            for coordinate in coordinates_points:
                coordinates.append(coordinate)
            points_used += points_len

    # малюємо АБО
    if len(coordinates) > 1:
        isAND = True
        # малюємо підпис до елемента АБО
        matrix_AND_amount = len(coordinates)
        matrix_AND_coord = coordinates[-1] + Coordinate(0, element_size)
        draw_text(pil_image, matrix_AND_coord, f"елемент")
        matrix_AND_coord = matrix_AND_coord + Coordinate(0, int(element_size * 0.5))
        draw_text(pil_image, matrix_AND_coord, f"{matrix_AND_amount}АБО")

        coordinates = connect_points(image, pil_image, coordinates, element_size,
                                     [],
                                     rectangle_text="1", force_Output="f0")
    else:
        matrix_AND_amount = ""
        isAND = False

    addition_text_coords = Coordinate(starting_coords.x, matrix_text.y) + Coordinate(element_size // 2, 0)

    # Формування тексту
    formatted_matrix = f"та функцій ({', '.join([f'І{matrix}' for matrix in all_matrix])}"
    if isAND:
        formatted_matrix += f", {matrix_AND_amount}АБО)"
    else:
        formatted_matrix += ")"

    # Додавання тексту в список
    addition_text = [
        "Числа в назві елементів ",
        formatted_matrix,
        " позначають кількість входів ",
        " в даний елемент"
    ]

    last_coord_addition_texts = []

    # Виведення тексту на зображення
    for element in addition_text:
        addition_text_coords += Coordinate(0, element_size // 2)
        last_coord_addition_text = draw_text(pil_image, addition_text_coords, str(element))
        last_coord_addition_texts.append(last_coord_addition_text.x)

    # Знаходження максимальної координати x
    max_text_x = max(last_coord_addition_texts)

    if len(coordinates) == 1:
        output_text_coords = coordinates[0] - Coordinate(element_size // 2, -element_size // 2)
        last_coord = draw_text(pil_image, output_text_coords, "вихiд")

        if max_text_x >= last_coord.x:
            last_coord.x = max_text_x

        last_coord += Coordinate(element_size, 0)

        dnf_text = f"ДНФ: f0={function}"
        DNF_text_coord = Coordinate(last_coord.x // 2 - get_text_size(dnf_text)[0] // 2,
                                    starting_coords.y - element_size // 4)
        draw_text(pil_image, DNF_text_coord, dnf_text)

        image_number_text = f"Рис. 3.1.2 Схема функції 3.1"
        image_number_coord = Coordinate(last_coord.x // 2 - get_text_size(image_number_text)[0] // 2,
                                        image_y - get_text_size(image_number_text)[1] // 2)
        draw_text(pil_image, image_number_coord, image_number_text)

        return crop_and_combine(image, pil_image, last_coord)


if __name__ == '__main__':
    main_function: str = "/cde v ab v abc"  # вхідна функція

    image = solve_Task_3_1_scheme(main_function)

    # num = 50
    # t = timeit.timeit(lambda: main(main_function), number=num)
    # print(t / 50)

    # зберігаємо зображення
    file_path = f'output_image.png'
    cv2.imwrite(file_path, image)

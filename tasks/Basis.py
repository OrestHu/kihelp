import os

import cv2
from PIL import Image, ImageDraw, ImageFont
import numpy as np


class Coordinate:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __eq__(self, other):
        if isinstance(other, Coordinate):
            return self.x == other.x and self.y == other.y
        return False

    def __str__(self):
        return f"({self.x}, {self.y})"

    def distance(self, amount):
        distance = ((self.x - amount.x) ** 2 + (self.y - amount.y) ** 2) ** 0.5
        return distance

    def __add__(self, amount: 'Coordinate'):
        new_x = self.x + amount.x
        new_y = self.y + amount.y
        return Coordinate(new_x, new_y)

    def __sub__(self, amount: 'Coordinate'):
        new_x = self.x - amount.x
        new_y = self.y - amount.y
        return Coordinate(new_x, new_y)

    def __mul__(self, amount: 'Coordinate'):
        new_x = self.x * amount.x
        new_y = self.y * amount.y
        return Coordinate(new_x, new_y)

    def __truediv__(self, amount: 'Coordinate'):
        new_x = self.x / amount.x
        new_y = self.y / amount.y
        return Coordinate(new_x, new_y)

    def __hash__(self):
        return hash((self.x, self.y))


def crop_image_cv2(cv_image, last_coord):
    x, y = last_coord.x, cv_image.shape[0]
    cropped_image = cv_image[:y, :x]
    return cropped_image


def crop_pil_image(pil_image, last_coord):
    x, y = last_coord.x, pil_image.size[1]
    cropped_pil_image = pil_image.crop((0, 0, x, y))
    return cropped_pil_image


def crop_and_combine(cv_image: cv2, pil_image, last_coord):
    cropped_image = crop_image_cv2(cv_image, last_coord)
    cropped_pil_image = crop_pil_image(pil_image, last_coord)

    overlay = np.array(cropped_pil_image)

    # Ensure the cropped logo and overlay have the same shape
    h, w = overlay.shape[:2]
    cropped_image = cropped_image[:h, :w]

    # Додавання альфа-каналу зображення до canvas
    alpha_s = overlay[:, :, 3] / 255.0
    alpha_l = 1.0 - alpha_s

    for c in range(0, 3):
        cropped_image[:, :, c] = (alpha_s * overlay[:, :, c] + alpha_l * cropped_image[:, :, c])

    return cropped_image


def draw_dot(canvas, coordinate: Coordinate, radius=2, color=(0, 0, 0), thickness=5):
    cv2.circle(canvas, (coordinate.x, coordinate.y), radius=radius, color=color, thickness=thickness)
    return coordinate + Coordinate(radius + int(thickness // 1.5), 0)


base_dir = os.path.dirname(os.path.abspath(__file__))
font_path_main = os.path.join(base_dir, "assets", "fonts", "arial.ttf")


def get_text_size(text, font_path=font_path_main, font_size=32):
    image = Image.new('RGB', (1, 1))
    draw = ImageDraw.Draw(image)
    font = ImageFont.truetype(font_path, font_size)
    text_size = draw.textbbox((0, 0), text, font)
    size = (text_size[2] - text_size[0], text_size[3] - text_size[1])
    return size


def draw_text(canvas, coordinate: Coordinate, text="", color=(0, 0, 0), thickness=4, font_size=32, addLine=False):
    # Завантаження шрифту
    if not os.path.exists(font_path_main):
        print("Файл шрифту не знайдено: {font_path_main}")
        font = ImageFont.load_default()
    else:
        font = ImageFont.truetype(font_path_main, font_size)

    line_coords_start = Coordinate(coordinate.x, coordinate.y)

    coordinate += Coordinate(0, -font_size)
    init_coord = coordinate

    for i, symbol in enumerate(text):
        # Створення маленького зображення для кожного символу
        draw = ImageDraw.Draw(canvas)

        if symbol == "/":
            bbox = draw.textbbox((0, 0), text[i + 1], font)
            size = (bbox[2] - bbox[0], bbox[3] - bbox[1])
            line_coordinate_start = (coordinate.x, coordinate.y + 2)
            line_coordinate_end = (line_coordinate_start[0] + size[0], line_coordinate_start[1])
            draw.line([line_coordinate_start, line_coordinate_end], fill=color, width=thickness)
        else:
            if symbol == "\\" and text[i + 1] == "n":
                size = get_text_size(text)
                coordinate = init_coord + Coordinate(0, size[1] + font_size // 4)
                init_coord = coordinate
            elif symbol == "n" and text[i - 1] == "\\":
                pass
            else:
                draw.text((coordinate.x, coordinate.y), symbol, font=font, fill=color)
                bbox = draw.textbbox((0, 0), symbol, font)
                size = (bbox[2] - bbox[0], bbox[3] - bbox[1])
                coordinate = Coordinate(coordinate.x + size[0], coordinate.y)
    if addLine:
        line_coords_end = Coordinate(line_coords_start.x, line_coords_start.y)
        text_size = get_text_size(text)
        line_coords_start -= Coordinate(0, font_size + 5)
        line_coords_end -= Coordinate(-text_size[0], font_size + 5)
        draw = ImageDraw.Draw(canvas)
        draw.line([(line_coords_start.x, line_coords_start.y), (line_coords_end.x, line_coords_end.y)], fill=color,
                  width=thickness)
    return coordinate


def draw_line(canvas, start_coord, end_coord, color=(0, 0, 0), thickness=2, start_text="", end_text="",
              text_canvas=Image.new("RGBA", (10, 10), (255, 255, 255, 0)),
              dot_start=False, dot_end=False):
    cv2.line(canvas, (start_coord.x, start_coord.y), (end_coord.x, end_coord.y), color, thickness)
    if start_text != "":
        text_coord = start_coord + Coordinate(thickness * 5, -thickness * 10)
        draw_text(text_canvas, text_coord, start_text)
    if end_text != "":
        text_coord = end_coord - Coordinate(thickness * 15, thickness * 10) - Coordinate(len(end_text) * thickness * 5,
                                                                                         0)
        draw_text(text_canvas, text_coord, end_text)
    if dot_start:
        draw_dot(canvas, start_coord, radius=3, thickness=10, color=(255, 255, 255))
        draw_dot(canvas, start_coord, radius=10, thickness=2)
    if dot_end:
        draw_dot(canvas, end_coord, radius=3, thickness=10, color=(255, 255, 255))
        draw_dot(canvas, end_coord, radius=10, thickness=2)

    return end_coord


def draw_rectangle(canvas,
                   top_coord,
                   down_coord=Coordinate(0, 0),
                   rectangle_size=20,
                   color=(0, 0, 0),
                   thickness=2,
                   dot_positions=(False, False, False),
                   text_inside="",
                   text_outside="",
                   text_canvas=Image.new("RGBA", (10, 10), (255, 255, 255, 0)),
                   ignore_dot=False):
    isSecondCoordGiven = True
    if down_coord == Coordinate(0, 0):
        isSecondCoordGiven = False
        down_coord = Coordinate(top_coord.x, top_coord.y + rectangle_size // 2)

    x1, y1 = top_coord.x, top_coord.y
    x2, y2 = down_coord.x, down_coord.y
    y1 -= rectangle_size // 2

    if not isSecondCoordGiven:
        height = rectangle_size
    else:
        height = y2 - y1 + rectangle_size // 2

    cv2.rectangle(canvas,
                  (x1, y1),
                  (x2 + rectangle_size, y1 + height),
                  color, thickness)

    dot_x = x1 + rectangle_size  # координата X крапки (права сторона квадрата)
    if not isSecondCoordGiven:
        dot_y = y1 + rectangle_size // 2
    else:
        all_y = [y1, y1 + height]
        dot_y = sum(all_y) // len(all_y)

    if text_inside != "":
        x, y = get_text_size(text_inside)
        x = (rectangle_size + x) // 2
        y = (height // 2 - y) // 2
        draw_text(text_canvas, Coordinate(dot_x - x, dot_y - y),
                  text_inside)
    if text_outside != "":
        if ignore_dot:
            is_add_Line = False
        else:
            is_add_Line = dot_positions[2]
        text_coord = Coordinate(dot_x, dot_y) - Coordinate(int(-rectangle_size * 0.1), 10)
        draw_text(text_canvas, text_coord, text_outside, addLine=is_add_Line)
    if dot_positions[0]:
        draw_dot(canvas, top_coord, radius=3, thickness=10, color=(255, 255, 255))
        draw_dot(canvas, top_coord, radius=10, thickness=2)
    if dot_positions[1]:
        draw_dot(canvas, down_coord, radius=3, thickness=10, color=(255, 255, 255))
        draw_dot(canvas, down_coord, radius=10, thickness=2)

    if dot_positions[2]:
        draw_dot(canvas, Coordinate(dot_x, dot_y), radius=3, thickness=10, color=(255, 255, 255))
        return draw_dot(canvas, Coordinate(dot_x, dot_y), radius=10, thickness=2)
    return Coordinate(dot_x, dot_y)

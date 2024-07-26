import re

from Basis import Coordinate, draw_line, draw_text, get_text_size, draw_rectangle


def draw_bus_Boolean(canvas, bus_start_coords: Coordinate, bus_size, basic_function, text_canvas):
    bus_combinations = re.findall(r'/[a-z]|[a-z]', basic_function)
    invertors = [element for element in bus_combinations if "/" in element]
    invertors = list(dict.fromkeys(invertors))

    inputs = [i for i in list(basic_function) if i != "/"]
    inputs = list(dict.fromkeys(inputs))

    end_coord = bus_start_coords + Coordinate(0, bus_size)
    start_coord = None
    for inp in inputs:
        start_coord = end_coord - Coordinate(bus_size, 0)
        draw_line(canvas, start_coord, end_coord, start_text=inp, text_canvas=text_canvas)
        end_coord = end_coord + Coordinate(0, bus_size)

    if start_coord:
        draw_text(text_canvas,
                  start_coord + Coordinate(int(-bus_size * 0.3), bus_size // 2),
                  "входи")

    elements_amount = len(bus_combinations)
    bus_end_coords = end_coord + Coordinate(0, (bus_size * elements_amount))
    bus_end_coords = draw_line(canvas, bus_start_coords, bus_end_coords, thickness=10, text_canvas=text_canvas)
    text_size = get_text_size("Шина")
    text_coords = (bus_end_coords - Coordinate(int(bus_size * 1.5), 0)) - Coordinate(text_size[0] // 2, -bus_size // 2)
    draw_text(text_canvas, text_coords, "Шина")

    text_size = get_text_size("Матриця НЕ")
    text_coords = (bus_end_coords - Coordinate(int(bus_size * 1.5), 0)) - Coordinate(text_size[0] // 2,
                                                                                     bus_size // 2 - text_size[1] - 10)

    bus_end_coords = draw_line(canvas, bus_end_coords, bus_end_coords - Coordinate(bus_size * 3, 0), thickness=10,
                               text_canvas=text_canvas)
    draw_line(canvas, bus_end_coords, Coordinate(bus_end_coords.x, bus_start_coords.y), thickness=10,
              text_canvas=text_canvas)

    # малюємо виходи з шини
    bus_exits = []
    for i, element in enumerate(bus_combinations):
        line_start = bus_start_coords + Coordinate(0, bus_size * (i + 1))
        line_end = line_start + Coordinate(bus_size, 0)
        line_end = draw_line(canvas, line_start, line_end, end_text=bus_combinations[i], text_canvas=text_canvas)
        bus_exits.append(line_end)

    # ставимо інвертори
    inverter_start = bus_end_coords

    for inverter in invertors:
        bus_end_coords = inverter_start - Coordinate(0, bus_size)
        bus_end_coords = draw_line(canvas,
                                   bus_end_coords,
                                   bus_end_coords + Coordinate(bus_size, 0),
                                   end_text=inverter[1], text_canvas=text_canvas)
        bus_end_coords = draw_rectangle(canvas, bus_end_coords, rectangle_size=bus_size,
                                        dot_positions=(False, False, True), text_inside="1", text_outside=inverter[1],
                                        text_canvas=text_canvas)
        draw_line(canvas, bus_end_coords, Coordinate(bus_start_coords.x, bus_end_coords.y), text_canvas=text_canvas)
        inverter_start -= Coordinate(0, bus_size)

    if len(invertors) >= 1:
        draw_text(text_canvas, text_coords, "Матриця НЕ")
    return bus_exits

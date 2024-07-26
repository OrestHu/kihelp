import os
from PIL import Image
from reportlab.pdfgen import canvas
from reportlab.lib.pagesizes import letter


def send_and_delete(user_id, working_directory, result_path):

    files = sorted(
        [file for file in working_directory.iterdir() if file.is_file() and str(user_id) in file.name],
        key=lambda f: f.stem[-1] if f.stem else ''
    )

    print(files)

    # Фільтруємо лише зображення
    images = [f for f in files if f.name.lower().endswith(('png', 'jpg', 'jpeg', 'bmp', 'gif'))]

    # Створюємо PDF файл
    output_pdf = (result_path / f'task_{user_id}.pdf')
    c = canvas.Canvas(str(output_pdf), pagesize=letter)

    page_width, page_height = letter

    y_position = page_height

    for image in images:
        img_path = os.path.join(working_directory, image)
        img = Image.open(img_path)

        # Отримуємо розміри зображення
        width, height = img.size

        # Перевірка ширини
        if width > page_width:
            aspect_ratio = height / width
            width = page_width
            height = int(width * aspect_ratio)

        # Перевірка висоти
        if height > page_height:
            aspect_ratio = width / height
            height = page_height
            width = int(height * aspect_ratio)

        # Якщо зображення не вміщується на поточній сторінці, переходимо на нову сторінку
        if y_position - height < 0:
            c.showPage()
            y_position = page_height

        y_position -= height

        # Визначаємо x_position для центрування зображення по ширині сторінки
        x_position = (page_width - width) / 2

        # Додаємо зображення на сторінку
        c.drawImage(img_path, x_position, y_position, width=width, height=height)
        img.close()
        os.remove(img_path)

    c.save()


def get_command(jar_file_path, *args):
    command = [
                  'java',
                  '-Djava.library.path=opencv/build/java/x64',
                  '-jar',
                  jar_file_path
              ] + list(args)
    return command

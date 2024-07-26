import subprocess
import sys

from pathlib import Path

import cv2

from Task3_1 import solve_Task_3_1_scheme
from functions import send_and_delete


def main():
    print(sys.argv)
    base_directory: Path = Path(__file__).resolve().parents[0]

    result_directory: Path = base_directory / "answer"
    working_directory = base_directory / 'jar'
    jar_file_path: str = "Task3_1.jar"

    # Перевіряємо, чи є аргументи
    if len(sys.argv) < 2:
        raise ValueError("Будь ласка, введіть аргументи.")

    a: str = "a"
    b: str = "b"
    c: str = "c"
    d: str = "d"
    e: str = "e"
    _function: str = sys.argv[1]
    tg_id: str = sys.argv[2]

    resilt = subprocess.run(["java", "-jar", jar_file_path, a, b, c, d, e, _function, tg_id],
                            stdout=subprocess.PIPE, text=True,
                            cwd=working_directory)

    print(resilt.stdout.strip())
    scheme = solve_Task_3_1_scheme(_function)
    print(scheme.shape)
    file_name = f"scheme_{tg_id}_0.png"
    scheme_directory = base_directory / 'jar' / 'result' / file_name
    cv2.imwrite(str(scheme_directory), scheme)

    send_and_delete(tg_id, scheme_directory.resolve().parents[0], result_directory)

if __name__ == "__main__":
    main()

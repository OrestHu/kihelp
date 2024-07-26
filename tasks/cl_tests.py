import subprocess
import sys
from pathlib import Path
from typing import Any


def main():
    working_directory: Path = Path(__file__).resolve().parents[0]
    result_directory: Path = working_directory / "answer"
    working_directory = working_directory / 'jar'
    jar_file_path: str = "TestForKL-1.0.jar"
    # Перевіряємо, чи є аргументи
    if len(sys.argv) < 2:
        raise ValueError("Будь ласка, введіть аргументи.")

    # Відкриваємо файл для запису
    with open(f'{str(result_directory)}\\task_{sys.argv[-1]}.txt', 'w') as file:
        # Записуємо аргументи у файл
        for arg in sys.argv[1:-1]:
            result: Any = subprocess.run(["java", "-jar", jar_file_path, arg], stdout=subprocess.PIPE, text=True,
                                         cwd=working_directory)
            if java_output := result.stdout.strip():
                file.write(java_output + '\n')
                print(java_output)
            print(result.stdout.strip())


if __name__ == "__main__":
    main()

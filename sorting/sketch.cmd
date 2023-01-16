:: Usage: sketch [FLAG] { FILENAME | DELAY }
:: =========================================
:: sketch without arguments runs the sketch with a default delay of 500
:: the -s flag with save the sketch as a gif and requires a file name argument
:: the -d flag specifies a delay between each frame

@echo off

set FLAG=%1
set FILENAME=%2
set DELAY=%2
set FOLDER=%~dp0gifs

if "%FLAG%" == "" goto run_sketch
if "%FLAG%" == "-s" goto save
if "%FLAG%" == "-d" goto set_delay
echo Invalid usage.
exit /b

:run_sketch
    processing-java --sketch=%~dp0 --run %*
    exit /b

:save
    if "%FILENAME%" == "" (
        echo Missing FILENAME.
        exit /b
    )

    call :run_sketch %*

    :: use ImageMagick to convert processing sketch frames into a gif
    magick convert -delay 20 -loop 0 *.tif %FOLDER%\%FILENAME%.gif
    echo Saved as "%FILENAME%.gif".

    :: clear files
    del /f /q %~dp0*.tif
    exit /b

:set_delay
    if "%DELAY%" == "" (
        echo Missing DELAY.
        exit /b
    )

    call :run_sketch %*
    exit /b
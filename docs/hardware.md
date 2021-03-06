# hardware

image

## schematics
![Schematics](https://github.com/warhog/fpvlaptracker/blob/master/hardware/schematics.png "Schematics")

## board
the eagle files are located in /hardware

## parts
all parts through hole!

[nodemcu dev board v3 lolin](https://www.banggood.com/V3-NodeMcu-Lua-WIFI-Development-Board-p-992733.html?rmmds=search)

[boscam RX5808 5,8 GHz receiver](https://www.banggood.com/FPV-5_8G-Wireless-Audio-Video-Receiving-Module-RX5808-p-84775.html?rmmds=search)

LED (i have chosen 5mm yellow)

2x resistor 1/4w 10k ohm

resistor 1/4w 270 ohm

resistor 1/4w 1k ohm

electrolytic capacitor 470µF 16v

[push button](https://www.reichelt.de/?ACTION=3;ARTICLE=27892;SEARCH=taster%203301)

[connector](https://www.reichelt.de/Schraubklemmen/AKL-101-02/3/index.html?ACTION=3&LA=446&ARTICLE=36605&GROUPID=7546&artnr=AKL+101-02&SEARCH=akl%2B)

### RX5808 5,8 GHz Receiver Mod
The RX5808 receiver needs to be modified to support the SPI protocol.
An excellent description can be found here:
https://github.com/sheaivey/rx5808-pro-diversity/blob/master/docs/rx5808-spi-mod.md

## case
the STL files are located in the /hardware folder.

## bluetooth tracker unit
the difference between the normal and the bluetooth tracker unit is the addition of a HC-06 bluetooth module that is attached to the RX and TX pins of the nodemcu module.

## setup software
tested with arduino 1.8.3 and the esp8266 targets added.

you need the ArduinoJson library installed (use arduino library manager)

## flash the firmware
the nodemcu board cannot be installed on the board during flashing. you need to flash it separatly.

## test

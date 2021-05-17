#include <Adafruit_NeoPixel.h>


// Which pin on the Arduino is connected to the NeoPixels?
// On a Trinket or Gemma we suggest changing this to 1
#define PIN_TR           9
#define PIN_TL           6
#define PIN_BR           7
#define PIN_BL           8

// How many NeoPixels are attached to the Arduino?
#define NUMTOPPIXELS      35
#define NUMBOTTOMPIXELS   25
#define RED               1
#define BLUE              2
#define RAINBOW           3
#define ORANGE            0

// Pattern types supported:
enum  pattern { NONE, RAINBOW_CYCLE, THEATER_CHASE, COLOR_WIPE, SCANNER, FADE };
// Patern directions supported:
enum  direction { FORWARD, REVERSE };

// NeoPattern Class - derived from the Adafruit_NeoPixel class

pattern  ActivePattern;  // which pattern is running
direction Direction;     // direction to run the pattern

unsigned long Interval;   // milliseconds between updates
unsigned long lastUpdate; // last update of position

uint32_t Color1, Color2;  // What colors are in use
uint16_t TotalSteps;  // total number of steps in the pattern
uint16_t Index;  // current step within the pattern
void (*OnComplete)();  // Callback on completion of pattern

// When we setup the NeoPixel library, we tell it how many pixels, and which pin to use to send signals.
// Note that for older NeoPixel strips you might need to change the third parameter--see the strandtest
// example for more information on possible values.
Adafruit_NeoPixel pixelsTR = Adafruit_NeoPixel(NUMTOPPIXELS, PIN_TR, NEO_GRBW + NEO_KHZ800);
Adafruit_NeoPixel pixelsTL = Adafruit_NeoPixel(NUMTOPPIXELS, PIN_TL, NEO_GRBW + NEO_KHZ800);
Adafruit_NeoPixel pixelsBR = Adafruit_NeoPixel(NUMBOTTOMPIXELS, PIN_BR, NEO_GRBW + NEO_KHZ800);
Adafruit_NeoPixel pixelsBL = Adafruit_NeoPixel(NUMBOTTOMPIXELS, PIN_BL, NEO_GRBW + NEO_KHZ800);

int delayval = 50;              // delay for half a second
String inputString = "";         // a string to hold incoming data
int color = 0;
int numPixels = 0;
boolean commandReady = false;  // whether the string is complete
void setup() {
  Serial.begin(250000);
  pixelsTR.begin();// This initializes the NeoPixel library.
  pixelsTL.begin();
  pixelsBR.begin();// This initializes the NeoPixel library.
  pixelsBL.begin();
  inputString.reserve(4);


  lightLeds(pixelsTR, pixelsTL, RAINBOW, NUMTOPPIXELS, delayval);
  lightLeds(pixelsBR, pixelsBL, BLUE, NUMBOTTOMPIXELS, delayval);
}


void loop() {
  if (commandReady)
  {
    lightLeds(pixelsTR, pixelsTL, color, numPixels, delayval);
    commandReady = false;
  }
  delay(20);
}
// Increment the Index and reset at the end
void Increment()
{
  if (Direction == FORWARD)
  {
    Index++;
    if (Index >= TotalSteps)
    {
      Index = 0;
      if (OnComplete != NULL)
      {
        OnComplete(); // call the comlpetion callback
      }
    }
  }
  else // Direction == REVERSE
  {
    --Index;
    if (Index <= 0)
    {
      Index = TotalSteps - 1;
      if (OnComplete != NULL)
      {
        OnComplete(); // call the comlpetion callback
      }
    }
  }
}
void serialEvent() {
  char inChar;
  while (Serial.available()) {
    // get the new byte:
    inChar = (char)Serial.read();
    if (inChar == ',') {
      color = inputString.toInt();
      inputString = "";
    }
    else if (inChar == '\n') {
      numPixels = inputString.toInt();
      inputString = "";
      commandReady = true;
    }
    else
    {
      inputString += inChar;
    }
  }
}
void RainbowCycle(uint8_t interval, direction dir = FORWARD)
{
  ActivePattern = RAINBOW_CYCLE;
  Interval = interval;
  TotalSteps = 255;
  Index = 0;
  Direction = dir;
}

void lightLeds(Adafruit_NeoPixel pixels, Adafruit_NeoPixel pixels2, int color, int numPixels, int delayval)
{
  //Serial.write(numPixels);

  if (color == RED)
  {
    Serial.write("red\n");
    for (uint16_t i = 0; i < pixels.numPixels(); i++) {
      pixels.setPixelColor(i, pixels.Color(255, 0, 0));
      pixels2.setPixelColor(i, pixels.Color(255, 0, 0));
      pixels.show(); // This sends the updated pixel color to the hardware.
      pixels2.show(); // This sends the updated pixel color to the hardware.
      delay(delayval); // Delay for a period of time (in milliseconds).
    }
  }
  else if (color == BLUE)
  {
    Serial.write("blue\n");
    for (uint16_t i = 0; i < pixels.numPixels(); i++) {
      pixels.setPixelColor(i, pixels.Color(0, 0, 255));
      pixels2.setPixelColor(i, pixels.Color(0, 0, 255));
      pixels.show(); // This sends the updated pixel color to the hardware.
      pixels2.show(); // This sends the updated pixel color to the hardware.
      delay(delayval); // Delay for a period of time (in milliseconds).
    }
  }
  else if (color == ORANGE)
  {
    Serial.write("orange\n");
    for (uint16_t i = 0; i < pixels.numPixels(); i++) {
      pixels.setPixelColor(i, pixels.Color(255, 50, 0));
      pixels2.setPixelColor(i, pixels.Color(255, 50, 0));
      pixels.show(); // This sends the updated pixel color to the hardware.
      pixels2.show(); // This sends the updated pixel color to the hardware.
      delay(delayval); // Delay for a period of time (in milliseconds).
    }
  }
  else if (color == RAINBOW)
  {
    Serial.write("rainbow/n");
    for (uint16_t i = 0; i < pixels.numPixels(); i++) {
      pixels.setPixelColor(i, (((i * 256 / pixels.numPixels()) + Index) & 255));
      pixels2.setPixelColor(i, (((i * 256 / pixels2.numPixels()) + Index) & 255));
      pixels.show(); // This sends the updated pixel color to the hardware.
      pixels2.show(); // This sends the updated pixel color to the hardware.
    }
    Increment();
  }
  else
  {
    Serial.write("default\n");
    for (uint16_t i = 0; i < pixels.numPixels(); i++) {
      pixels.setPixelColor(i, pixels.Color(100, 100, 100));
      pixels2.setPixelColor(i, pixels.Color(100, 100, 100));
      pixels.show(); // This sends the updated pixel color to the hardware.
      pixels2.show(); // This sends the updated pixel color to the hardware.
      delay(delayval); // Delay for a period of time (in milliseconds).
    }
  }
  if (numPixels <= NUMTOPPIXELS)
  {
    for (uint16_t i = numPixels; i < pixels.numPixels(); i++) {
      pixels.setPixelColor(i, pixels.Color(255, 90, 0));
      pixels2.setPixelColor(i, pixels.Color(255, 90, 0));
      pixels.show(); // This sends the updated pixel color to the hardware.
      pixels2.show(); // This sends the updated pixel color to the hardware.
      //delay(delayval); // Delay for a period of time (in milliseconds).
    }
  }
}

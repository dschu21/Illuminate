#include <Adafruit_NeoPixel.h>

#include <SoftEasyTransfer.h>
#include <SoftwareSerial.h>

#define PIN 6
#define LEDNUM 144
Adafruit_NeoPixel strip = Adafruit_NeoPixel(LEDNUM, PIN, NEO_GRB + NEO_KHZ800);

SoftwareSerial BTSerial(10, 11); // TX | RX 
SoftEasyTransfer ET; 

//Easy Transfer Struct
struct RECEIVE_DATA_STRUCTURE{
  //put your variable definitions here for the data you want to receive
  //THIS MUST BE EXACTLY THE SAME ON THE OTHER ARDUINO
  int one;
  int two;
  int thr;
  int fou;
  int fiv;
  int six;
  int sev;
  int eig;
  int nin;
  int ten;
  int ele;
  int twe;
  int thi;


};
RECEIVE_DATA_STRUCTURE mydata;

  int first = 0, second = 0, third = 0, fourth = 0, fifth = 0, sixth = 0, seventh = 0, eighth = 0, ninth = 0, tenth = 0, eleventh = 0, twelfth = 0, thirteenth = 0;
  int brightness = 56;
  
  uint16_t j = 0, q = 0;
  
void setup() {
    
    pinMode(6, OUTPUT);
    pinMode(9, OUTPUT);  // this pin will pull the HC-05 pin 34 (key pin) HIGH to switch module to AT mode
    digitalWrite(9, HIGH);
    Serial.begin(115200);
    Serial.println("Start");
    BTSerial.begin(115200);  // HC-05 default speed in AT command more
    ET.begin(details(mydata), &BTSerial);
    
    strip.begin();
    strip.setBrightness(brightness);
    strip.show(); // Initialize all pixels to 'off'

}

void loop() {
     if (ET.receiveData()){
        //assign software serial struct objects to variables
        //message is 4 bits long
        //protocol = first[XXXX XXXX] second[XXYY YYYY] third[YYYY RLUD] forth[SFE0 0000]
        first = mydata.one;
        second = mydata.two;
        third = mydata.thr;
        fourth = mydata.fou;
        fifth = mydata.fiv;
        sixth = mydata.six;
        seventh = mydata.sev;
        eighth = mydata.eig;
        ninth = mydata.nin;
        tenth = mydata.ten; 
        eleventh = mydata.ele; 
        twelfth = mydata.twe;
        thirteenth = mydata.thi;
    }
        switch(first) {
          case 0:   white();
                    break;
          case 1:   solid();
                    break;
          case 2:   straight();
                    break;
          case 3:   point(100);
                    break;
          case 4:   breathe(15);
                    break;
          case 5:   chaseRainbow(50);
                    break;
          default:
                    break;
        }   
       
     
}

void white() {
    erase();
      for (int i = 0 ; i<144; i++){
        if(i%3==0)
          strip.setPixelColor(i, brightness/2, brightness/2, brightness/2);
       }
       strip.show();
}

void solid() {
      for (int i = 0 ; i<144; i++){
          strip.setPixelColor(i, second, third, fourth);
       }
       strip.show();
}

void straight() {
       for (int i = 0 ; i<144; i+=4){
          strip.setPixelColor(i, second, third, fourth);
       }

       for (int i = 1 ; i<144; i+=4){
          strip.setPixelColor(i, fifth, sixth, seventh);
       }

       for (int i = 2 ; i<144; i+=4){
          strip.setPixelColor(i, eighth, ninth, tenth);
        }

       for (int i = 3 ; i<144; i+=4){
          strip.setPixelColor(i, eleventh, twelfth, thirteenth);
       }

          
       strip.show();
        
}


void point(uint8_t wait) {    
        for (int i = 0 ; i<144/4; i++){
          strip.setPixelColor(i, first, second, third);
        }

        for (int i = 144/4 ; i<144/2; i++){
          strip.setPixelColor(i, fifth, sixth, seventh);
        }

        for (int i = 144/2 ; i<3*144/4; i++){
          strip.setPixelColor(i, eighth, ninth, tenth);
        }

        for (int i = 3*144/4 ; i<144; i++){
          strip.setPixelColor(i, eleventh, twelfth, thirteenth);
        }

        strip.show();

}

void breathe(uint8_t wait) {
 uint16_t i;
 
    for(i=0; i< strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    delay(wait);
    
    if(j<256*3)
      j++;
    else
      j=0;
}

void chaseRainbow(uint8_t wait) {

      for (int i=0; i < strip.numPixels(); i=i+3) {
        strip.setPixelColor(i+q, Wheel( (i+j) % 255));    //turn every third pixel on
      }
      strip.show();
      delay(wait);
      
      for (int i=0; i < strip.numPixels(); i=i+3) {
        strip.setPixelColor(i+q, 0);        //turn every third pixel off
      }

      if(q<3)
        q++;
      else
        q=0;
    

    if(j<256)   // cycle all 256 colors in the wheel
      j++;
    else
      j=0;
      
  
}


void erase() {
   for (int i = 0 ; i<144;i+=1)
          strip.setPixelColor(i, 0, 0, 0);
}

uint32_t Wheel(byte WheelPos) {
  WheelPos = 255 - WheelPos;
  if(WheelPos < 85) {
    return strip.Color(255 - WheelPos * 3, 0, WheelPos * 3);
  }
  if(WheelPos < 170) {
    WheelPos -= 85;
    return strip.Color(0, WheelPos * 3, 255 - WheelPos * 3);
  }
  WheelPos -= 170;
  return strip.Color(WheelPos * 3, 255 - WheelPos * 3, 0);
}

#include <Adafruit_NeoPixel.h>

#include <SoftEasyTransfer.h>
#include <SoftwareSerial.h>

#define PIN 6
#define LEDNUM 60
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
  int light1_0, light1_1, light2_0, light2_1, light3_0, light3_1, light4_0, light4_1;
  
void setup() {
    pinMode(6, OUTPUT);
    pinMode(9, OUTPUT);  // this pin will pull the HC-05 pin 34 (key pin) HIGH to switch module to AT mode
    digitalWrite(9, HIGH);
    Serial.begin(115200);
    Serial.println("Start");
    BTSerial.begin(115200);  // HC-05 default speed in AT command more
    ET.begin(details(mydata), &BTSerial);
    
    strip.begin();
    strip.setBrightness(64);
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

        switch(first) {
          case 0:   straight();
                    break;
          case 1:   pulse();
                    break;
          case 2:   wave(50);
                    break;
          case 3:   point(25);
                    break;
          case 4:   breathe(20);
                    break;
        }
       
     }
}

void straight() {
       for (int i = 0 ; i<60; i+=4)
          strip.setPixelColor(i, second, third, fourth);
       for (int i = 1 ; i<60; i+=4)
          strip.setPixelColor(i, fifth, sixth, seventh);
       for (int i = 2 ; i<60; i+=4)
          strip.setPixelColor(i, eighth, ninth, tenth);
       for (int i = 3 ; i<60; i+=4)
          strip.setPixelColor(i, eleventh, twelfth, thirteenth);
          
       strip.show();
        
}

void pulse() {
       for (int i = 0 ; i<60; i++)
          strip.setPixelColor(i, second, third, fourth);
       flash();
       
       for (int i = 0 ; i<60; i++)
          strip.setPixelColor(i, fifth, sixth, seventh);
       flash();
       
       for (int i = 0 ; i<60; i++)
          strip.setPixelColor(i, eighth, ninth, tenth);
       flash();
       
       for (int i = 0 ; i<60; i++)
          strip.setPixelColor(i, eleventh, twelfth, thirteenth);
       flash();
          
}

void wave(uint8_t wait) {       
       for (int i = -5 ; i < 64; i++) {
          for (int j = i-4 ; j<i+4; j++){
              if( j>= 0 && j<=60)
                strip.setPixelColor(j, second, third, fourth);
          }
          strip.show();
          delay(wait);
       }
       delay(250);
        
       for (int i = -5 ; i < 64; i++) {
          for (int j = i-4 ; j<i+4; j++){
              if( j>= 0 && j<=60)
                strip.setPixelColor(j, fifth, sixth, seventh);
          }
          strip.show();
          delay(wait);
       }
       delay(250);
  
       for (int i = -5 ; i < 64; i++) {
          for (int j = i-4 ; j<i+4; j++){
              if( j>= 0 && j<=60)
                strip.setPixelColor(j, eighth, ninth, tenth);
          }
          strip.show();
          delay(wait);
       }
       delay(250);
       
     
        
       for (int i = -5 ; i < 64; i++) {
          for (int j = i-4 ; j<i+4; j++){
              if( j>= 0 && j<=60)
                strip.setPixelColor(j, eleventh, twelfth, thirteenth);
          }
          strip.show();
          delay(wait);
       }
       delay(250);
       
 
      

}

void point(uint8_t wait) {    
        for (int i = -2 ; i<60/2 + 2; i++){
          for (int j = i-2 ; j < i+2; j++){
            if( j>= 0 && j<=60){
              strip.setPixelColor(j, second, third, fourth);
              strip.setPixelColor(60-j, second, third, fourth);
            }
          }
          strip.show();
          delay(wait);
        }
        delay(50); 
        triangleErase();
        delay(100); 

        for (int i = -2 ; i<60/2 + 2; i++){
          for (int j = i-2 ; j < i+2; j++){
            if( j>= 0 && j<=60){
              strip.setPixelColor(j, fifth, sixth, seventh);
              strip.setPixelColor(60-j,fifth, sixth, seventh);
            }
          }
          strip.show();
          delay(wait);
        }
        delay(50); 
        triangleErase();
        delay(100); 

        for (int i = -2 ; i<60/2 + 2; i++){
          for (int j = i-2 ; j < i+2; j++){
            if( j>= 0 && j<=60){
              strip.setPixelColor(j, eighth, ninth, tenth);
              strip.setPixelColor(60-j, eighth, ninth, tenth);
            }
          }
          strip.show();
          delay(wait);
        }
        delay(50); 
        triangleErase();
        delay(100); 

        for (int i = -2 ; i<60/2 + 2; i++){
          for (int j = i-2 ; j < i+2; j++){
            if( j>= 0 && j<=60){
              strip.setPixelColor(j, eleventh, twelfth, thirteenth);
              strip.setPixelColor(60-j, eleventh, twelfth, thirteenth);
            }
          }
          strip.show();
          delay(wait);
        }
        delay(50); 
        triangleErase();
        delay(100); 

        
          
          
}

void breathe(uint8_t wait) {
 uint16_t i, j;

  for(j=0; j<256*5; j++) { // 5 cycles of all colors on wheel
    for(i=0; i< strip.numPixels(); i++) {
      strip.setPixelColor(i, Wheel(((i * 256 / strip.numPixels()) + j) & 255));
    }
    strip.show();
    delay(wait);
  }
}
        


void flash() {
       strip.show();
       delay(2500);
       erase();
       strip.show();
       delay(1000);
}

void triangleErase() {
        for (int i = 0 ; i<30; i++){
              strip.setPixelColor(30-i,0, 0, 0);
              strip.setPixelColor(30+i,0, 0, 0);
              strip.show();
              delay(25);
        }
}



void erase() {
   for (int i = 0 ; i<60;i+=1)
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



//블루투스 관련
#include <SoftwareSerial.h>
SoftwareSerial BT(2, 3);
String cmd;

//온습도계 관련
#include <SimpleDHT.h>
SimpleDHT11 dht11(4);
byte temperature = 0;
byte humidity = 0;
int err = SimpleDHTErrSuccess;
int last_temp;
int last_humi;


//토양수분센서 관련
int soilHumidity = 0;
#define A_SOIL_HUMI A0
int last_soil;

//LCD 관련 
#include <LiquidCrystal_I2C.h>/*LiquidCrystal_I2C*/
#define LCD_I2C_ADDR 0x27 
LiquidCrystal_I2C lcd(LCD_I2C_ADDR, 16, 2);

//워터펌프 관련
#define O_PUMP_A 6
#define O_PUMP_B 5

//RGB 관련 변수 선언
#define O_RGB_R 10
#define O_RGB_G 9
#define O_RGB_B 11


//////////////////////////////// 사용자 정의 함수 ///////////////////////////////////

//LCD 화면 출력 함수
void printLcd() {
  lcd.setCursor(0, 0);
  lcd.print(" Hellosoft Farm");
  lcd.setCursor(0, 1);
  lcd.print("T: ");
  lcd.print((int)temperature);
  lcd.print("*C / ");
  lcd.print("H: ");
  lcd.print(soilHumidity);
  lcd.print("%");

}

//토양습도 계산 함수
void calcSoilHumidity() {
  soilHumidity = map(analogRead(A_SOIL_HUMI), 1000, 400, 0, 100);
  if(soilHumidity > 100) soilHumidity = 100;
  else if(soilHumidity < 0) soilHumidity = 0;
}

//온도 습도 계산 함수
void calcTemp(){
    int err = SimpleDHTErrSuccess;
  if ((err = dht11.read(&temperature, &humidity, NULL)) != SimpleDHTErrSuccess) {
    return;
  }
}

//워터펌프 작동 함수
void WaterPump(){
    delay(2000);
    lcd.clear();
    lcd.noBacklight();
    for (int i = 0; i < 230; i++) 
    {
      analogWrite(O_PUMP_A, i);
      delay(5);
    }
    delay(500);
    analogWrite(O_PUMP_A, 0);
    analogWrite(O_PUMP_B, 0);
    delay(100);

  lcd.init();
  lcd.backlight();
}

void SendSensor(){
   
  //블루투스 출력 
  //온도 3자리 / 습도 3자리 / 토양수분 3자리 형태로 출력함. 예시) Sensor026056080

  String message = "Sensor";
  
  //온도 3자리 출력
  if((int)temperature<100) message+="0";
  if((int)temperature<10) message+="0";
  message+=((int)temperature);
  
  //습도 3자리 출력
  if((int)humidity<100) message+="0";
  if((int)humidity<10) message+="0";
  message+=((int)humidity);

  //토양수분 3자리 출력
  if((int)soilHumidity<100) message+="0";
  if((int)soilHumidity<10) message+="0";
  message+=((int)soilHumidity);

  BT.println(message);

  last_temp = (int)temperature;
  last_humi = (int)humidity;
  last_soil = (int)soilHumidity; 
}

void RGBChange(){
  int red = cmd.substring(3,6).toInt();
  int green = cmd.substring(6,9).toInt();
  int blue = cmd.substring(9,12).toInt();
  Serial.print(red);
  Serial.print("/");
    Serial.print(green);
  Serial.print("/");
    Serial.print(blue);
  Serial.println("/");
  
  analogWrite(O_RGB_R,red);
  analogWrite(O_RGB_G,green);
  analogWrite(O_RGB_B,blue);
}

/////////////////////////////// SETUP //////////////////////////////////

void setup() {
  Serial.begin(9600);
  BT.begin(9600);
  
  pinMode(O_PUMP_A, OUTPUT);
  pinMode(O_PUMP_B, OUTPUT);
  pinMode(O_RGB_R, OUTPUT);
  pinMode(O_RGB_G, OUTPUT);
  pinMode(O_RGB_B, OUTPUT);
  pinMode(12,OUTPUT);
  pinMode(13,OUTPUT);
  
  analogWrite(O_RGB_R, 125);
  analogWrite(O_RGB_G, 125);
  analogWrite(O_RGB_B, 125);
  
  analogWrite(O_PUMP_A, 0);
  analogWrite(O_PUMP_B, 0);

  digitalWrite(13,HIGH);
  digitalWrite(12,HIGH);
  
  lcd.init();
  lcd.backlight();
  
  delay(2000);
}

//////////////////////////////// LOOP ////////////////////////////////////

void loop() {

  //온도,습도,토양수분 계산하고 LCD에 출력하기
  calcTemp();
  calcSoilHumidity();
  printLcd();
  
  /*
  //토양 수분 센서 습도가 1~19 값이면 자동으로 워터펌프가 작동합니다.
  if (soilHumidity < 20 && soilHumidity >1) {
    BT.println("WaterPump");
    WaterPump();
  } 
  */

  //만약 센서값이 달라지면 블루투스로 전송
  if ((int)temperature!=last_temp || (int)humidity!=last_humi || soilHumidity!=last_soil){
    SendSensor();
  }

  //만약 블루투스로 신호가 들어오면 작동합니다.
  if(BT.available()){
    cmd = BT.readStringUntil('\n');
    delay(100);
    Serial.println(cmd);

    if(cmd.indexOf("WaterPump")>-1) {
      BT.println("WaterPump");
      WaterPump();
    }
    
    else if (cmd.indexOf("RGB")>-1){
      BT.println("RGB");
      RGBChange();
    }    
    
    else{
      Serial.println(cmd);
    }
  }

  delay(100);
  
}

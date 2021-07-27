import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore
import time
import serial   # <1>

cred = credentials.Certificate("sparkProject.json")
firebase_admin.initialize_app(cred)
db = firestore.client()
doc_ref = db.collection(u'sparkProject').document(u'sparkSensor')

def main():
    port1 = serial.Serial("/dev/ttyACM0", baudrate=9600, timeout=None)   # <2> 온습도
    port2 = serial.Serial("/dev/ttyACM1", baudrate=9600, timeout=None)   # <2> 토양습도압력  
    
    data1=0
    data2=0
    data3=""
    data4=0
    data5=0

    while True:
        line1 = port1.readline()  # <3>온습도미세먼지
        line2 = port2.readline()  # <3>토양습도압력 
        arr1 = line1.split()  # <4>  온습도 
        arr2 = line2.split()  # <4>  토양 

        dataType6 = arr1[2].decode()[:2]
        data6 = float(arr1[1])    # <7> 온습도미세먼지
        dataType7 = arr2[2].decode()[:2]
        data7 = int(arr2[1])    # <7> 토양습도
        
        if dataType6 == '%':
            print("Hum: %.1f %%" % data6)
            data1=data6

        elif dataType6 == 'C':
            print("Temp: %.1f C" % data6)
            data2=data6
        
        elif dataType6 == 'ug':
            print("Dust: %.1f ug/m3" % data6)
            if data6 < 1:
                data3="좋음"

            elif data6 < 160:
                data3="보통"

            elif data6 > 200:
                data3="나쁨"  
        
        if dataType7 == 'A':
            print("Asensor: %d" % data7)
            data4=data7

        elif dataType7 == 'P':
            print("Press: %d" % data7)
            data5=data7
        
        doc_ref.set({
            u'hum' : data1,
            u'temp' : data2,
            u'dust' : data3,
            u'asensor' : data4,
            u'press' : data5
            })

        time.sleep(0.01)
        

if __name__ == "__main__":
    main()


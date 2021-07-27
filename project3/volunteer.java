package com.example.project3;

//테이블이라고 생각하고, 테이블에 들어갈 속성값을 넣기
//파이어베이스는 RDBMS와 다르기 때문에 테이블이라는 개념이 없음. 원래는 키값이라고 부름
public class volunteer {
    String name;
    String phone;
    String email;

    public volunteer(){} //이건 기본적으로 쓰더라구요.


    //get, set 함수는 커스텀 리스트 뷰를 사용하시는 분들과.. 필요하신 분만 작성하시면 좋습니다.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //값을 추가할때 쓰는 함수, MainActivity에서 addanimal함수에서 사용할 것임.
    public volunteer(String name, String phone, String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
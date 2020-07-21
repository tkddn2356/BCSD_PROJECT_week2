//package com.sangwookim.validator;
//
//
//import com.sangwookim.domain.User;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//public class UserValidator  implements Validator {
//    //어떤 타입의 객체를 검증할 때 이 객체의 클래스가 이 Validator가 검증할 수 있는 클래스인 지를 판단하는 매서드
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return User.class.equals(clazz);
//    }
//    // 첫 번째 파라미터로 전달받은 객체를 검증하고 오류 결과를 Errors에 담는 기능을 정의한다.
//    @Override
//    public void validate(Object target, Errors errors) {
//        User user = (User)target;
//        String domainName = errors.getObjectName();
//        if(domainName.equals("joinUser") || domainName.equals("modifyUser")){
//            if(user.getPassword().equals(user.getPassword_confirm()) == false) {
//                errors.rejectValue("password", "notEqual");
//                errors.rejectValue("password_confirm", "notEqual");
//            }
//        }
//        if(domainName.equals("joinUser")){
//            if(user.isUser_exist()==false){
//                errors.rejectValue("id", "notCheckExist");
//            }
//        }
//    }
//}

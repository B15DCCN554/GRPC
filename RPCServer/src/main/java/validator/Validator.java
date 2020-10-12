package validator;

public abstract class Validator {

    public boolean isEmpty(String str){
        if(str == null) return true;
        if(str.isEmpty()) return true;
        if(str.length()>0){
            for (char s: str.toCharArray()){
                if(" ".charAt(0) != s){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

}

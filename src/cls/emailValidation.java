/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Admin
 */
public class emailValidation {
    public static boolean EmailValidat(String email) {
   boolean result = true;
   try {
      InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
   } catch (AddressException ex) {
      result = false;
   }
   return result;
}

public static boolean phoneNoV(String phoneNumber){
        boolean isValid = false;
        /* Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn
            ^\\(? : May start with an option "(" .
            (\\d{3}): Followed by 3 digits.
            \\)? : May have an optional ")" 
            [- ]? : May have an optional "-" after the first 3 digits or after optional ) character. 
            (\\d{3}) : Followed by 3 digits. 
            [- ]? : May have another optional "-" after numeric digits.
            (\\d{4})$ : ends with four digits.

            Examples: Matches following phone numbers:
            (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890

        */
        //Initialize reg ex for phone number. 
        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches())
        {
            isValid = true;
        }
        return isValid;
    }
}

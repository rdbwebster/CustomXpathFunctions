package com.example.reusable.asset;

/**
 * XSLT Specific Mapper function.
 * Each XSLT Mapper function requires a corresponding public static method from a public static class.
 * The function name and method name must match.
 * Function is registered in a file with the naming convention ext-mapper-xpath-functions-config.xml
 */
public class CheckDept {

public static String checkDept(String input){

    if (input == null || input.length() == 0)
            return "999";
    else return input;

}

}
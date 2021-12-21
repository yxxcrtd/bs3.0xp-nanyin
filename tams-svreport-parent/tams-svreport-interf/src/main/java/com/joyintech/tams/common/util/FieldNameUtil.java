package com.joyintech.tams.common.util;

/**
 * 字段转换工具类
 */

public class FieldNameUtil {
    /**
     * 分割驼峰字段
     * @param str
     * @param separator
     * @return
     */
    private static String separateCamelCase(String str, String separator) {
        StringBuilder translation = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (Character.isUpperCase(character) && translation.length() != 0) {
                translation.append(separator);
            }
            translation.append(character);
        }
        return translation.toString();
    }
    /**
     * 下划线转换为驼峰
     */
    public static String underscore2CamelCase(String str) {
        StringBuilder translation = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (character == '_')
                continue;
            if (translation.length() != 0 && str.charAt(i - 1) == '_') {
                translation.append(Character.toUpperCase(character));
            } else {
                translation.append(character);
            }
        }
        return translation.toString();
    }

    /**
     * 驼峰转换为下划线并大写
     * @param str
     * @return
     */
    public static String camelCase2Underscore(String str) {
        return separateCamelCase(str, "_").toUpperCase();
    }

    /**
     * 首字母大写
     * @param str
     * @return
     */
    public static String upperCaseFirstLetter(String str){
        StringBuilder fieldstrBuilder = new StringBuilder();
        int index = 0;
        char firstCharacter = str.charAt(index);
        while (index < str.length() - 1) {
            if (Character.isLetter(firstCharacter)) {
                break;
            }

            fieldstrBuilder.append(firstCharacter);
            firstCharacter = str.charAt(++index);
        }

        if (index == str.length()) {
            return fieldstrBuilder.toString();
        }

        if (!Character.isUpperCase(firstCharacter)) {
            String modifiedTarget = modifyString(Character.toUpperCase(firstCharacter), str, ++index);
            return fieldstrBuilder.append(modifiedTarget).toString();
        } else {
            return str;
        }
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String lowerCaseFirstLetter(String str){
        StringBuilder fieldstrBuilder = new StringBuilder();
        int index = 0;
        char firstCharacter = str.charAt(index);
        while (index < str.length() - 1) {
            if (Character.isLetter(firstCharacter)) {
                break;
            }

            fieldstrBuilder.append(firstCharacter);
            firstCharacter = str.charAt(++index);
        }

        if (index == str.length()) {
            return fieldstrBuilder.toString();
        }

        if (!Character.isLowerCase(firstCharacter)) {
            String modifiedTarget = modifyString(Character.toLowerCase(firstCharacter), str, ++index);
            return fieldstrBuilder.append(modifiedTarget).toString();
        } else {
            return str;
        }
    }

    /**
     * 修改字符串
     * @param firstCharacter
     * @param srcString
     * @param indexOfSubstring
     * @return
     */
    private static String modifyString(char firstCharacter, String srcString, int indexOfSubstring) {
        return (indexOfSubstring < srcString.length())
                ? firstCharacter + srcString.substring(indexOfSubstring)
                : String.valueOf(firstCharacter);
    }
}


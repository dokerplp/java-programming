package com.company.exceptions;

/**
 * Throws if there are more then 100 execute script commands in script
 */
public class InfinityRecursionException extends RuntimeException{

    public InfinityRecursionException(){
        System.err.println("Произошла глубокая рекурсия, просьба разойтись, мероприятие незаконно");
    }
}

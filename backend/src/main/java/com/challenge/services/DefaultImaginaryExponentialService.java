package com.challenge.services;

import com.challenge.datatransfer.imaginarynumber.ImaginaryResult;
import com.challenge.interfaces.datatransfer.ImaginaryNumber;
import com.challenge.interfaces.services.ImaginaryExponentialService;

public class DefaultImaginaryExponentialService implements ImaginaryExponentialService {
    public ImaginaryNumber calculateExponential(
            Double A, Double B) 
            throws IllegalArgumentException {
        if (A == null || B == null)
            throw new IllegalArgumentException("Numerical argumentos to exponetination may not be null.");

        Double realComponent = A * Math.sin(B);
        Double imaginaryComponent = A * Math.cos(B);

        return new ImaginaryResult(realComponent, imaginaryComponent);
    }
}

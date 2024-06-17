package com.challenge.services;

import com.challenge.datatransfer.CollatzOutput;
import com.challenge.interfaces.services.CollatzService;
import com.challenge.interfaces.structure.Result;

public class DefaultCollatzService implements CollatzService {
    public Result<CollatzOutput> calculateCollatz(Double initialValue, Integer step) {
        if (initialValue == null || step == null)
            return new Result.Error<>("Either one of or both initial value and step are null or undefined.");

        if (initialValue <= 0 || step <= 0)
            return new Result.Error<>("Either one of or both initial value and step are zeroes.");
        
        Double outputValue = initialValue;
        for (int i = 0; i < step; i++) {
            outputValue = (outputValue % 2 == 0)
                ? outputValue / 2
                : outputValue * 3 + 1;
        }

        var result = new CollatzOutput(outputValue);
        return new Result.Ok<>(result);
    }
}

package com.challenge.interfaces.services;

import com.challenge.datatransfer.CollatzOutput;
import com.challenge.interfaces.structure.Result;

public interface CollatzService {
    Result<CollatzOutput> calculateCollatz(Double initialValue, Integer step);
}

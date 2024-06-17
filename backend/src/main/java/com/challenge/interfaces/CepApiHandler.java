package com.challenge.interfaces;

import com.challenge.interfaces.structure.Result;

public interface CepApiHandler {
    Result<Boolean> consultCep(String cep);
}

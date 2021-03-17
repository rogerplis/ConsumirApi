package com.example.consumirapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsumirJson {
    public static List<CEP> jsonDados(String conteudo){
        List<CEP> cepList = new ArrayList<>();
    try{

        JSONArray jsonArray = new JSONArray(conteudo);
        JSONObject jsonObject = null;
        for ( int i = 0; i < jsonArray.length(); i++ ) {
            jsonObject = jsonArray.getJSONObject(i);
            CEP cep = new CEP();

            cep.setLogradouro(jsonObject.getString("logradouro"));
            cep.setLocalidade(jsonObject.getString("localidade"));
            cep.setBairro(jsonObject.getString("bairro"));
            cep.setComplemento(jsonObject.getString("complemento"));
            cep.setUf(jsonObject.getString("uf"));
            cep.setCep(jsonObject.getString("cep"));
            cep.setDDD(jsonObject.getString("ddd"));
            cep.setGia(jsonObject.getString("gia"));

            cepList.add(cep);
        }

    }catch (Exception e){
        e.printStackTrace();
    }
        return cepList;
    }
}


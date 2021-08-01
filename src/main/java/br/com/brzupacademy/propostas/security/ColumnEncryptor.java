package br.com.brzupacademy.propostas.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ColumnEncryptor implements AttributeConverter<String, String> {

    @Value("${encryptor.column.password}")
    private String password;
    @Value("${encryptor.column.salt}")
    private String salt;

    @Override
    public String convertToDatabaseColumn(String value) {
        return Encryptors.queryableText(this.password, this.salt).encrypt(value);
    }

    @Override
    public String convertToEntityAttribute(String value) {
        return Encryptors.queryableText(this.password, this.salt).decrypt(value);

    }
}

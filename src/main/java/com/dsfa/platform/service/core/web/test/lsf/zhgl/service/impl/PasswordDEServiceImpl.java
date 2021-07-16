package com.dsfa.platform.service.core.web.test.lsf.zhgl.service.impl;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.service.core.web.test.lsf.zhgl.service.PasswordDEService;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;
import org.springframework.stereotype.Service;

@Service
public class PasswordDEServiceImpl implements PasswordDEService {

    @Override
    public String encode(String str) throws PlatformCoreException {
        return str.concat("lsf");
    }

    @Override
    public String decode(String str) throws PlatformCoreException {
        return str.substring(0, str.length() - 3);
    }

    @Override
    public String encode(PersistData persistData) throws PlatformCoreException {
        String passwordKey = "test_lsf_zhgl.password";
        String password = persistData.getStr(passwordKey);
        String encoded = this.encode(password);
        persistData.set(passwordKey, encoded);
        return encoded;
    }

    @Override
    public String decode(PersistData persistData) throws PlatformCoreException {
        String passwordKey = "test_lsf_zhgl.password";
        String password = persistData.getStr(passwordKey);
        String decoded = this.decode(password);
        persistData.set(passwordKey, decoded);
        return decoded;
    }
}

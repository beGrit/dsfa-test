package com.dsfa.platform.service.core.kit.checker;

import com.dsfa.platform.service.core.exception.PlatformCoreException;
import com.dsfa.platform.starter.meta.core.model.persist.PersistData;

public interface DataChecker {
    void checkAll(PersistData json) throws PlatformCoreException;

    void checkRequired(PersistData json) throws PlatformCoreException;

    void checkUnRequired(PersistData json) throws PlatformCoreException;

    void customCheck(PersistData json) throws PlatformCoreException;
}

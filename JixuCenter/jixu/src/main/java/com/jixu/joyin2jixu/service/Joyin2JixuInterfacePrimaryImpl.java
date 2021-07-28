package com.jixu.joyin2jixu.service;

import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auth 何阳阳
 * @date 2021/7/28
 */
@Lazy
@Service
@Primary
public class Joyin2JixuInterfacePrimaryImpl extends Joyin2JixuInterfaceService implements Joyin2JixuInterface {
    @Override
    public List<SelectOptionDto> getSecurityDtoByAssetType(String... assetType) {
        return this.getSecurityDtoByAssetTypes(assetType.clone());
    }
}

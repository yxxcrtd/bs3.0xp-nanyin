package com.joyintech.tams.invest.common.redisinit.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IAssetDuration;

public class ImplAssetDuration extends BaseEntity implements IAssetDuration {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8864524547957977972L;

    private String assetCode;

    private MockAssetInfoDto mockAssetInfoDto;

    @Override
    public String getAssetCode() {
        return this.assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    @Override
    public MockAssetInfoDto getMockAssetInfoDto() {
        return mockAssetInfoDto;
    }

    public void setMockAssetInfoDto(MockAssetInfoDto mockAssetInfoDto) {
        this.mockAssetInfoDto = mockAssetInfoDto;
    }
}

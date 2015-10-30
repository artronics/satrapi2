package artronics.sdwn.server.repositories.jpa;

import artronics.sdwn.server.model.SdwnNetworkSetting;
import artronics.sdwn.server.repositories.SdwnNetworkSettingRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaSdwnNetworkSettingRepo implements SdwnNetworkSettingRepo
{
    @PersistenceContext
    EntityManager em;

    @Override
    public SdwnNetworkSetting find(Long networkId)
    {
        return em.find(SdwnNetworkSetting.class, networkId);
    }

    @Override
    public SdwnNetworkSetting createSdwnNetworkSetting(SdwnNetworkSetting setting)
    {
        SdwnNetworkSetting defaultSetting = new SdwnNetworkSetting();
        defaultSetting.setDescription("default setting");
        em.persist(defaultSetting);
        return defaultSetting;
    }
}

import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/sales-force-user">
        <Translate contentKey="global.menu.entities.salesForceUser" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/sales-target">
        <Translate contentKey="global.menu.entities.salesTarget" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/sales-target-filter">
        <Translate contentKey="global.menu.entities.salesTargetFilter" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/incentive-scheme">
        <Translate contentKey="global.menu.entities.incentiveScheme" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/retailer-mapping">
        <Translate contentKey="global.menu.entities.retailerMapping" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/order-cache">
        <Translate contentKey="global.menu.entities.orderCache" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/achivement-cache">
        <Translate contentKey="global.menu.entities.achivementCache" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/incentive">
        <Translate contentKey="global.menu.entities.incentive" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;

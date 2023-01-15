import salesForceUser from 'app/entities/sales-force-user/sales-force-user.reducer';
import salesTarget from 'app/entities/sales-target/sales-target.reducer';
import salesTargetFilter from 'app/entities/sales-target-filter/sales-target-filter.reducer';
import incentiveScheme from 'app/entities/incentive-scheme/incentive-scheme.reducer';
import retailerMapping from 'app/entities/retailer-mapping/retailer-mapping.reducer';
import orderCache from 'app/entities/order-cache/order-cache.reducer';
import achivementCache from 'app/entities/achivement-cache/achivement-cache.reducer';
import incentive from 'app/entities/incentive/incentive.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  salesForceUser,
  salesTarget,
  salesTargetFilter,
  incentiveScheme,
  retailerMapping,
  orderCache,
  achivementCache,
  incentive,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;

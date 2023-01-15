import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SalesForceUser from './sales-force-user';
import SalesTarget from './sales-target';
import SalesTargetFilter from './sales-target-filter';
import IncentiveScheme from './incentive-scheme';
import RetailerMapping from './retailer-mapping';
import OrderCache from './order-cache';
import AchivementCache from './achivement-cache';
import Incentive from './incentive';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="sales-force-user/*" element={<SalesForceUser />} />
        <Route path="sales-target/*" element={<SalesTarget />} />
        <Route path="sales-target-filter/*" element={<SalesTargetFilter />} />
        <Route path="incentive-scheme/*" element={<IncentiveScheme />} />
        <Route path="retailer-mapping/*" element={<RetailerMapping />} />
        <Route path="order-cache/*" element={<OrderCache />} />
        <Route path="achivement-cache/*" element={<AchivementCache />} />
        <Route path="incentive/*" element={<Incentive />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};

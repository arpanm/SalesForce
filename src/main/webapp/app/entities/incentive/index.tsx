import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Incentive from './incentive';
import IncentiveDetail from './incentive-detail';
import IncentiveUpdate from './incentive-update';
import IncentiveDeleteDialog from './incentive-delete-dialog';

const IncentiveRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Incentive />} />
    <Route path="new" element={<IncentiveUpdate />} />
    <Route path=":id">
      <Route index element={<IncentiveDetail />} />
      <Route path="edit" element={<IncentiveUpdate />} />
      <Route path="delete" element={<IncentiveDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default IncentiveRoutes;

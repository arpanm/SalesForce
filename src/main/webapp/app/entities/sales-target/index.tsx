import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SalesTarget from './sales-target';
import SalesTargetDetail from './sales-target-detail';
import SalesTargetUpdate from './sales-target-update';
import SalesTargetDeleteDialog from './sales-target-delete-dialog';

const SalesTargetRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<SalesTarget />} />
    <Route path="new" element={<SalesTargetUpdate />} />
    <Route path=":id">
      <Route index element={<SalesTargetDetail />} />
      <Route path="edit" element={<SalesTargetUpdate />} />
      <Route path="delete" element={<SalesTargetDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SalesTargetRoutes;

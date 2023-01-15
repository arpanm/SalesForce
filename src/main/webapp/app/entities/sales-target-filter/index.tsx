import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SalesTargetFilter from './sales-target-filter';
import SalesTargetFilterDetail from './sales-target-filter-detail';
import SalesTargetFilterUpdate from './sales-target-filter-update';
import SalesTargetFilterDeleteDialog from './sales-target-filter-delete-dialog';

const SalesTargetFilterRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<SalesTargetFilter />} />
    <Route path="new" element={<SalesTargetFilterUpdate />} />
    <Route path=":id">
      <Route index element={<SalesTargetFilterDetail />} />
      <Route path="edit" element={<SalesTargetFilterUpdate />} />
      <Route path="delete" element={<SalesTargetFilterDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SalesTargetFilterRoutes;

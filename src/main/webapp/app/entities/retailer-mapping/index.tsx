import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import RetailerMapping from './retailer-mapping';
import RetailerMappingDetail from './retailer-mapping-detail';
import RetailerMappingUpdate from './retailer-mapping-update';
import RetailerMappingDeleteDialog from './retailer-mapping-delete-dialog';

const RetailerMappingRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<RetailerMapping />} />
    <Route path="new" element={<RetailerMappingUpdate />} />
    <Route path=":id">
      <Route index element={<RetailerMappingDetail />} />
      <Route path="edit" element={<RetailerMappingUpdate />} />
      <Route path="delete" element={<RetailerMappingDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RetailerMappingRoutes;

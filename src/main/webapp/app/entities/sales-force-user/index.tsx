import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SalesForceUser from './sales-force-user';
import SalesForceUserDetail from './sales-force-user-detail';
import SalesForceUserUpdate from './sales-force-user-update';
import SalesForceUserDeleteDialog from './sales-force-user-delete-dialog';

const SalesForceUserRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<SalesForceUser />} />
    <Route path="new" element={<SalesForceUserUpdate />} />
    <Route path=":id">
      <Route index element={<SalesForceUserDetail />} />
      <Route path="edit" element={<SalesForceUserUpdate />} />
      <Route path="delete" element={<SalesForceUserDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SalesForceUserRoutes;

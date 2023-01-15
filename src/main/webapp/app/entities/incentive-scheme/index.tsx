import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import IncentiveScheme from './incentive-scheme';
import IncentiveSchemeDetail from './incentive-scheme-detail';
import IncentiveSchemeUpdate from './incentive-scheme-update';
import IncentiveSchemeDeleteDialog from './incentive-scheme-delete-dialog';

const IncentiveSchemeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<IncentiveScheme />} />
    <Route path="new" element={<IncentiveSchemeUpdate />} />
    <Route path=":id">
      <Route index element={<IncentiveSchemeDetail />} />
      <Route path="edit" element={<IncentiveSchemeUpdate />} />
      <Route path="delete" element={<IncentiveSchemeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default IncentiveSchemeRoutes;

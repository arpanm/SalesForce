import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AchivementCache from './achivement-cache';
import AchivementCacheDetail from './achivement-cache-detail';
import AchivementCacheUpdate from './achivement-cache-update';
import AchivementCacheDeleteDialog from './achivement-cache-delete-dialog';

const AchivementCacheRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AchivementCache />} />
    <Route path="new" element={<AchivementCacheUpdate />} />
    <Route path=":id">
      <Route index element={<AchivementCacheDetail />} />
      <Route path="edit" element={<AchivementCacheUpdate />} />
      <Route path="delete" element={<AchivementCacheDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AchivementCacheRoutes;

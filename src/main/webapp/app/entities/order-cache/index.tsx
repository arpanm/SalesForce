import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import OrderCache from './order-cache';
import OrderCacheDetail from './order-cache-detail';
import OrderCacheUpdate from './order-cache-update';
import OrderCacheDeleteDialog from './order-cache-delete-dialog';

const OrderCacheRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<OrderCache />} />
    <Route path="new" element={<OrderCacheUpdate />} />
    <Route path=":id">
      <Route index element={<OrderCacheDetail />} />
      <Route path="edit" element={<OrderCacheUpdate />} />
      <Route path="delete" element={<OrderCacheDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OrderCacheRoutes;

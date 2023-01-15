import dayjs from 'dayjs';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';
import { Category } from 'app/shared/model/enumerations/category.model';

export interface IOrderCache {
  id?: number;
  orderId?: string | null;
  orderStatus?: OrderStatus | null;
  orderDate?: string | null;
  articleId?: string | null;
  category?: Category | null;
  brand?: string | null;
  model?: string | null;
  articlePrice?: number | null;
  articleQty?: number | null;
  retailerId?: string | null;
  retailerState?: string | null;
  isActive?: boolean | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  createdBy?: string | null;
  updatedBy?: string | null;
}

export const defaultValue: Readonly<IOrderCache> = {
  isActive: false,
};

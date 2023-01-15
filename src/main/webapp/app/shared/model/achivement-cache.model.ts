import dayjs from 'dayjs';
import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';
import { Category } from 'app/shared/model/enumerations/category.model';

export interface IAchivementCache {
  id?: number;
  startDate?: string | null;
  endDate?: string | null;
  articleId?: string | null;
  category?: Category | null;
  brand?: string | null;
  model?: string | null;
  retailerId?: string | null;
  retailerState?: string | null;
  orderQty?: number | null;
  totalOrderValue?: number | null;
  isActive?: boolean | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  createdBy?: string | null;
  updatedBy?: string | null;
  salesAgent?: ISalesForceUser | null;
}

export const defaultValue: Readonly<IAchivementCache> = {
  isActive: false,
};

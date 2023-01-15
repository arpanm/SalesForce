import dayjs from 'dayjs';
import { ISalesTarget } from 'app/shared/model/sales-target.model';
import { Category } from 'app/shared/model/enumerations/category.model';
import { FilterJoinType } from 'app/shared/model/enumerations/filter-join-type.model';

export interface ISalesTargetFilter {
  id?: number;
  articleId?: string | null;
  category?: Category | null;
  brand?: string | null;
  model?: string | null;
  articleMinPrice?: number | null;
  articleMaxPrice?: number | null;
  perOrderArticleMinQty?: number | null;
  perOrderArticleMaxQty?: number | null;
  perRetailerArticleMinQty?: number | null;
  perRetailerArticleMaxQty?: number | null;
  perRetailerDailyArticleMinQty?: number | null;
  perRetailerDailyArticleMaxQty?: number | null;
  state?: string | null;
  join?: FilterJoinType | null;
  isActive?: boolean | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  createdBy?: string | null;
  updatedBy?: string | null;
  target?: ISalesTarget | null;
}

export const defaultValue: Readonly<ISalesTargetFilter> = {
  isActive: false,
};

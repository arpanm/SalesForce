import dayjs from 'dayjs';
import { ISalesTargetFilter } from 'app/shared/model/sales-target-filter.model';
import { IIncentiveScheme } from 'app/shared/model/incentive-scheme.model';
import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';
import { SalesTargetType } from 'app/shared/model/enumerations/sales-target-type.model';

export interface ISalesTarget {
  id?: number;
  type?: SalesTargetType | null;
  startDate?: string;
  endDate?: string;
  targetValue?: number;
  isActive?: boolean | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  createdBy?: string | null;
  updatedBy?: string | null;
  filters?: ISalesTargetFilter[] | null;
  incentiveSchemes?: IIncentiveScheme[] | null;
  approvedBy?: ISalesForceUser | null;
}

export const defaultValue: Readonly<ISalesTarget> = {
  isActive: false,
};

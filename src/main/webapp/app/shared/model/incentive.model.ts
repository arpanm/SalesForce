import dayjs from 'dayjs';
import { IIncentiveScheme } from 'app/shared/model/incentive-scheme.model';
import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';

export interface IIncentive {
  id?: number;
  achivementPercent?: number | null;
  incentiveValue?: number | null;
  isActive?: boolean | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  createdBy?: string | null;
  updatedBy?: string | null;
  scheme?: IIncentiveScheme | null;
  salesAgent?: ISalesForceUser | null;
  approvedBy?: ISalesForceUser | null;
}

export const defaultValue: Readonly<IIncentive> = {
  isActive: false,
};

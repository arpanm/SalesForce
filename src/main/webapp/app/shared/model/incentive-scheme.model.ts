import dayjs from 'dayjs';
import { IIncentive } from 'app/shared/model/incentive.model';
import { ISalesTarget } from 'app/shared/model/sales-target.model';
import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';
import { IncentiveSchemeType } from 'app/shared/model/enumerations/incentive-scheme-type.model';

export interface IIncentiveScheme {
  id?: number;
  type?: IncentiveSchemeType | null;
  minAchivementPercent?: number | null;
  maxAchivementPercent?: number | null;
  value?: number | null;
  isActive?: boolean | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  createdBy?: string | null;
  updatedBy?: string | null;
  incentives?: IIncentive[] | null;
  target?: ISalesTarget | null;
  approvedBy?: ISalesForceUser | null;
}

export const defaultValue: Readonly<IIncentiveScheme> = {
  isActive: false,
};

import dayjs from 'dayjs';
import { ISalesTarget } from 'app/shared/model/sales-target.model';
import { IIncentiveScheme } from 'app/shared/model/incentive-scheme.model';
import { IRetailerMapping } from 'app/shared/model/retailer-mapping.model';
import { IAchivementCache } from 'app/shared/model/achivement-cache.model';
import { IIncentive } from 'app/shared/model/incentive.model';
import { Role } from 'app/shared/model/enumerations/role.model';

export interface ISalesForceUser {
  id?: number;
  name?: string;
  email?: string;
  phone?: number;
  role?: Role | null;
  dateOfJoining?: string | null;
  dateOfExit?: string | null;
  isActive?: boolean | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  createdBy?: string | null;
  updatedBy?: string | null;
  subordinates?: ISalesForceUser[] | null;
  approvedSalesTargets?: ISalesTarget[] | null;
  approvedIncentiveSchemes?: IIncentiveScheme[] | null;
  retailerMappings?: IRetailerMapping[] | null;
  approvedRetailerMappings?: IRetailerMapping[] | null;
  achivementCaches?: IAchivementCache[] | null;
  incentives?: IIncentive[] | null;
  approvedIncentives?: IIncentive[] | null;
  manager?: ISalesForceUser | null;
}

export const defaultValue: Readonly<ISalesForceUser> = {
  isActive: false,
};

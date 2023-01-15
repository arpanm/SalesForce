import dayjs from 'dayjs';
import { ISalesForceUser } from 'app/shared/model/sales-force-user.model';

export interface IRetailerMapping {
  id?: number;
  name?: string | null;
  prmId?: string | null;
  agentCode?: string | null;
  pincode?: number | null;
  state?: string | null;
  region?: string | null;
  registedOn?: string | null;
  mappedOn?: string | null;
  mappedTill?: string | null;
  isActive?: boolean | null;
  createdAt?: string | null;
  updatedAt?: string | null;
  createdBy?: string | null;
  updatedBy?: string | null;
  relationshipManager?: ISalesForceUser | null;
  approvedBy?: ISalesForceUser | null;
}

export const defaultValue: Readonly<IRetailerMapping> = {
  isActive: false,
};

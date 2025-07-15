
type UUID = string;

export interface Order {
  id: UUID;
  userId: string;
  configurationId: UUID;
  orderDate: string;
  finalPrice: number;
  status: string
  // ...
}

export interface Configuration {
  id: string;
  userId: string;
  carId: string;
  selectedOptionIds: string[]; // <== GENAU SO!
  totalPrice: number;
  createdAt: string;
  ordered: boolean;
}


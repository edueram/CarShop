
type UUID = string;

export interface Caroption {
  id: UUID;
  option_type: string;
  option_value: string;
  additional_price: number;
  // ...
}

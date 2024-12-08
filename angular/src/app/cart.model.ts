export interface Cart{
    customerId: number | null,
    productId: number,
    productName: string,
    quantity: number,
    price: number,
    totalPrice : number
}
/**
 * Created by juliepel on 14/06/2018.
 */
import { AuthGuard } from './auth.guards';
import { AccountGuard } from './account.guards';

export const guards: any[] = [
  AuthGuard,
  AccountGuard
];

export * from './auth.guards';
export * from './account.guards';

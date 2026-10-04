-- Hulas Smart Fabricator V2
-- PostgreSQL/Supabase foundation schema.
-- Run only after creating a Supabase project. Never store passwords in this database.

create extension if not exists pgcrypto;

create table if not exists public.fabricators (
  id uuid primary key references auth.users(id) on delete cascade,
  mobile text unique not null,
  full_name text not null,
  firm_name text,
  district text,
  pan_vat text,
  address text,
  status text not null default 'pending' check (status in ('pending','active','suspended')),
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

create table if not exists public.products (
  id uuid primary key default gen_random_uuid(),
  code text unique not null,
  name text not null,
  grade text check (grade in ('202','304')),
  shape text,
  size text,
  duty text check (duty in ('light','heavy')),
  length_m numeric not null default 6,
  weight_kg numeric not null,
  active boolean not null default true,
  created_at timestamptz not null default now()
);

create table if not exists public.rates (
  id uuid primary key default gen_random_uuid(),
  grade text unique not null check (grade in ('202','304')),
  rate_per_kg numeric(12,2) not null,
  vat_percent numeric(5,2) not null default 13,
  updated_at timestamptz not null default now()
);

create table if not exists public.orders (
  id uuid primary key default gen_random_uuid(),
  fabricator_id uuid not null references public.fabricators(id),
  status text not null default 'pending' check (status in ('pending','confirmed','processing','dispatched','delivered','cancelled')),
  total_weight_kg numeric(12,3) not null default 0,
  basic_amount numeric(14,2) not null default 0,
  vat_amount numeric(14,2) not null default 0,
  discount_amount numeric(14,2) not null default 0,
  payable_amount numeric(14,2) not null default 0,
  notes text,
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

create table if not exists public.order_items (
  id uuid primary key default gen_random_uuid(),
  order_id uuid not null references public.orders(id) on delete cascade,
  product_id uuid references public.products(id),
  specification text not null,
  quantity integer not null check (quantity > 0),
  weight_kg numeric(12,3) not null,
  unit_rate numeric(12,2) not null,
  amount numeric(14,2) not null
);

create table if not exists public.points_ledger (
  id uuid primary key default gen_random_uuid(),
  fabricator_id uuid not null references public.fabricators(id),
  points numeric(12,3) not null,
  type text not null check (type in ('earn','redeem','adjustment','reversal')),
  reference_type text,
  reference_id uuid,
  description text,
  created_at timestamptz not null default now()
);

create table if not exists public.qr_codes (
  id uuid primary key default gen_random_uuid(),
  code text unique not null,
  product_id uuid references public.products(id),
  weight_kg numeric(12,3) not null,
  status text not null default 'active' check (status in ('active','used','void')),
  used_by uuid references public.fabricators(id),
  used_at timestamptz,
  created_at timestamptz not null default now()
);

create table if not exists public.rewards (
  id uuid primary key default gen_random_uuid(),
  name text not null,
  points_required numeric(12,3) not null,
  active boolean not null default true,
  stock integer,
  created_at timestamptz not null default now()
);

create table if not exists public.reward_redemptions (
  id uuid primary key default gen_random_uuid(),
  fabricator_id uuid not null references public.fabricators(id),
  reward_id uuid not null references public.rewards(id),
  points_spent numeric(12,3) not null,
  status text not null default 'requested' check (status in ('requested','approved','fulfilled','rejected')),
  created_at timestamptz not null default now()
);

-- Critical: points are calculated from the ledger, not trusted from the browser.
create or replace view public.fabricator_point_balances as
select fabricator_id, coalesce(sum(
  case when type in ('earn','adjustment') then points
       when type in ('redeem','reversal') then -points
       else 0 end
),0) as balance
from public.points_ledger
group by fabricator_id;

-- Enable RLS before exposing tables through a client.
alter table public.fabricators enable row level security;
alter table public.orders enable row level security;
alter table public.order_items enable row level security;
alter table public.points_ledger enable row level security;
alter table public.reward_redemptions enable row level security;

-- Policies should be added deliberately with the business/admin roles.
-- Do NOT ship permissive "true" policies.

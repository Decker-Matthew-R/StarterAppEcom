import { render, screen, waitFor } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import LandingPage from '../LandingPage';
import * as metricsClient from '../../metrics/client/MetricsClient';
import { MemoryRouter } from 'react-router';

describe('render app', () => {
  const metricsClientMock = vi
    .spyOn(metricsClient, 'saveMetricEvent')
    .mockImplementation(() => Promise.resolve());

  const renderApp = () =>
    render(
      <MemoryRouter>
        <LandingPage />
      </MemoryRouter>,
    );

  it('should render app images', async () => {
    renderApp();

    const countButton = screen.getByRole('button', { name: 'count is 0' });

    expect(countButton).toBeVisible();

    countButton.click();

    await waitFor(() => {
      expect(screen.getByRole('button', { name: 'count is 1' })).toBeVisible();
    });
    expect(metricsClientMock).toHaveBeenCalledOnce();
  });
});
